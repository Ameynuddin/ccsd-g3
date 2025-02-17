import React, { useEffect, useState } from "react";
import { Link } from "react-router-dom";
import axios from "axios";
import Row from "react-bootstrap/Row";
import Col from "react-bootstrap/Col";
import { Card, Button } from "react-bootstrap";
import Rating from "../components/Rating";
import { useContext } from 'react';
import { Store } from '../Store';

function AllProdScreen() {
  const [products, setProducts] = useState([]);
  const { state, dispatch: ctxDispatch } = useContext(Store);
  const { cart: { cartItems } } = state;

  useEffect(() => {
    // Fetch products from MongoDB database
    axios
      .get(`${process.env.REACT_APP_API_URL}/api/products`)
      .then((response) => {
        setProducts(response.data);
      })
      .catch((error) => {
        console.error("Error fetching products:", error);
      });
  }, []);

  const addToCartHandler = async (product) => {
     console.log("Product being added:", product);
     console.log("Product ID:", product?._id || product?.id);

     if (!product || (!product._id && !product.id)) {
       console.error('Product or product ID is undefined', product);
       return;
     }

     const productId = product._id || product.id;
     const existItem = cartItems.find((x) => (x._id || x.id) === productId);
     const quantity = existItem ? existItem.quantity + 1 : 1;  // Increment if product exists

     try {
       const { data } = await axios.get(`${process.env.REACT_APP_API_URL}/api/products/${productId}`);
       console.log("Product data from API:", data);

       if (data.countInStock < quantity) {
         window.alert('Sorry. Product is out of stock');
         return;
       }

       ctxDispatch({
         type: 'CART_ADD_ITEM',
         payload: {
           ...product,
           quantity,  // Use the incremented quantity
           _id: productId  // Ensure consistent ID field
         },
       });
     } catch (error) {
       console.error("Error in addToCartHandler:", error);
     }
   };

  return (
    <div>
      <Row className="flex-wrap mt-4">
        {products.map((product) => (
          <Col key={product.slug} sm={6} md={4} lg={3} className="mb-3">
            <Card>
              <Link to={`/product/${product.slug}`}>
                <Card.Img
                  variant="top"
                  src={product.image}
                  alt={product.name}
                  style={{ width: "160px", height: "200px" }}
                />
              </Link>
              <Card.Body>
                <Link to={`/product/${product.slug}`}>
                  <Card.Title>{product.name}</Card.Title>
                </Link>
                <Rating
                  rating={product.rating}
                  numReviews={product.numReviews}
                />
                <Card.Text>${product.price}</Card.Text>
                {product.countInStock === 0 ? (
                  <Button variant="light" disabled>
                    Out of stock
                  </Button>
                ) : (
                  <Button onClick={() => addToCartHandler(product)}>
                    Add to cart
                  </Button>
                )}
              </Card.Body>
            </Card>
          </Col>
        ))}
      </Row>
    </div>
  );
}

export default AllProdScreen;
