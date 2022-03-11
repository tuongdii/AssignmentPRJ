/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package duyvtt.common;

/**
 *
 * @author DELL
 */
public final class Constants {

    private Constants() {
    }
    
    public static final class LoginFeature {

        private LoginFeature() {
        }

        public static final String SEARCH_PAGE_USER = "searchPageUser";
        public static final String SEARCH_PAGE_ADMIN = "searchPageAdmin";
        public static final String INVALID_PAGE = "invalid";
    }

    public static final class AutoLoginFeature {

        private AutoLoginFeature() {
        }

        public static final String LOGIN_PAGE = "login";
        public static final String SEARCH_PAGE_USER = "searchPageUser";
        public static final String SEARCH_PAGE_ADMIN = "searchPageAdmin";
    }

    public static final class SearchLastnameFeature {

        private SearchLastnameFeature() {
        }
        
        public static final String SEARCH_PAGE_ADMIN = "searchPageAdmin";
        public static final String SEARCH_PAGE_USER = "searchPageUser";
    }

    public static final class DeleteAccountFeature {

        private DeleteAccountFeature() {
        }

        public static final String SEARCH_LATS_NAME_SERVLET = "searchAccountAction";
    }

    public static final class UpdateAccountFeature {

        private UpdateAccountFeature() {
        }
        
        public static final String SEARCH_LATS_NAME_SERVLET = "searchAccountAction";
        public static final String PROFILE_PAGE = "profilePage";
    }

    public static final class AddItemToCartFeature {

        private AddItemToCartFeature() {
        }
        
        public static final String SHOPPING_PAGE = "shop";
    }

    public static final class LoadProductFeature {

        private LoadProductFeature() {
        }
        
        public static final String SHOPPING_PAGE = "shopPage";
    }

    public static final class LogoutFeature {

        private LogoutFeature() {
        }

        public static final String LOGIN_PAGE = "login";
    }

    public static final class RegisterFeature {

        private RegisterFeature() {
        }

        public static final String LOGIN_PAGE = "login";
        public static final String ERROR_PAGE = "registerPage";
    }

    public static final class RemoveItemFeature {

        private RemoveItemFeature() {
        }

        public static final String VIEW_CART_PAGE = "viewCart";
    }
    
    public static final class UpdateItemFeature{

        private UpdateItemFeature() {
        }
        
        public static final String CONFIRM_REMOVE = "removeItemsConfirm";
        public static final String VIEW_CART = "viewCart"; 
    }
    
    public static final class CheckoutFeature {

        private CheckoutFeature() {
        }

        public static final String SHOP_PAGE = "shop";
        public static final String VIEW_CART = "viewCart";
        public static final String CHECK_OUT = "checkout";
    }
    
    public static final class InitParam{

        private  InitParam() {
        }
        
        public static final String LOG4J = "LOG4J_PROPERTIES_FILE_LOCATION";
        public static final String SITEMAPS = "SITEMAPS_PROPERTIES_FILE_LOCATION";
        public static final String AUTHENTICATION = "AUTHENTICATION_PROPERTIES_FILE_LOCATION";
        public static final String ADMIN_AUTHOR = "ADMIN_AUTHORIZATION_PROPERTIES_FILE_LOCATION";
        public static final String USER_AUTHOR = "USER_AUTHORIZATION_PROPERTIES_FILE_LOCATION";
    }
    
    public static final class FilterFeature{

        private FilterFeature() {
        }
        
        public static final String LOGIN_PAGE = "login";
        public static final String SEARCH_PAGE_ADMIN = "searchPageAdmin";
        public static final String SEARCH_PAGE_USER = "searchPageUser";
    }
}
