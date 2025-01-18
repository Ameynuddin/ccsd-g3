import Card from 'react-bootstrap/Card';
import Button from 'react-bootstrap/Button';
import { Link } from 'react-router-dom';
import Rating from './Rating';
import axios from 'axios';
import { useContext } from 'react';
import { Store } from '../Store';

function Product(props) {
    const { product } = props;

    const { state, dispatch: ctxDispatch } = useContext(Store);
    const {
        cart: { cartItems },
    } = state;

    const addToCartHandler = async (product) => {
        console.log("Product being added:", product);
        console.log("Product ID:", product?._id || product?.id);

        if (!product || (!product._id && !product.id)) {
               console.error('Product or product ID is undefined', product);
               return;
        }

        const productId = product._id || product.id;
        const existItem = cartItems.find((x) => (x._id || x.id) === productId);
        const quantity = existItem ? existItem.quantity + 1 : 1;  // Increment if exists

        const existItem = cartItems.find((x) => x._id === product._id);
        const quantity = existItem ? existItem.quantity + 1 : 1;
        const { data } = await axios.get(`${process.env.REACT_APP_API_URL}/api/products/${product._id}`);

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
        <Card className='p-3'>
            <Link to={`/product/${product.slug}`}>
                <img src={product.image} className="card-img-top" alt={product.name} style={{ maxWidth: '100%', height: 'auto' }} />
            </Link>
            <Card.Body>
                <Link to={`/product/${product.slug}`}>
                    <Card.Title>{product.name}</Card.Title>
                </Link>
                <Rating rating={product.rating} numReviews={product.numReviews} />
                <Card.Text>${product.price}</Card.Text>
                {product.countInStock === 0 ? (
                    <Button variant="primary" disabled>
                        Out of stock
                    </Button>
                ) : (
                    <Button onClick={() => addToCartHandler(product)}>Add to cart</Button>
                )}
            </Card.Body>
        </Card>
    );
}
export default Product;