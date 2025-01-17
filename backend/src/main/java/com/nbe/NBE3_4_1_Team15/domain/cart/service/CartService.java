package src.main.java.com.nbe.NBE3_4_1_Team15.domain.cart.service;

import com.nbe.NBE3_4_1_Team15.domain.cartProduct.entity.CartProduct;
import com.nbe.NBE3_4_1_Team15.domain.product.entity.Product;
import src.main.java.com.nbe.NBE3_4_1_Team15.domain.cart.entity.Cart;
import src.main.java.com.nbe.NBE3_4_1_Team15.domain.cart.repository.CartRepository;

@Service
public class CartService {

    private final CartRepository cartRepository;
    private final CartProductRepository cartProductRepository;

    public CartService(CartRepository cartRepository, CartProductRepository cartProductRepository){
        this.cartRepository = cartRepository;
        this.cartProductRepository = cartProductRepository;
    }


    // 1. 장바구니 상품 추가
    public Cart addProductToCart(Cart cart, Product product, int quantity){
        CartProduct cartProduct = new CartProduct(cart, product, quantity);
        cart.getCartProducts().add(cartProduct);
        cartProductRepository.save(cartProduct);
        return cartRepository.save(cart);
    }

    // 2. 장바구니 조회
    public Cart getCart(Long cartId) throws IllegalAccessException {
        return cartRepository.findById(cartId)
                .orElseThrow(() -> new IllegalAccessException("장바구니를 찾을 수 없습니다."));
    }

    // 3. 장바구니 수정
    public CartProduct updateCartProduct(Long cartProductId, int newQuantity) throws IllegalAccessException {
        CartProduct cartProduct = cartProductRepository.findById(cartProductId)
                .orElseThrow(() -> new IllegalAccessException("장바구니 상품이 존재하지 않습니다."));
        cartProduct.setQuantity(newQuantity);
        return cartProductRepository.save(cartProduct);
    }

    // 4. 장바구니 상품 삭제
    public void deleteCartProduct(Long cartProductId){
        cartProductRepository.deleteById(cartProductId);
    }
}