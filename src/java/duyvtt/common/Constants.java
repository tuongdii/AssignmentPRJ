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
public class Constants {

    public class loginFeature {

        public static final String SEARCH_PAGE_USER = "searchPageUser";
        public static final String SEARCH_PAGE_ADMIN = "searchPageAdmin";
        public static final String INVALID_PAGE = "invalid";
    }

    public class autoLoginFeature {

        public static final String LOGIN_PAGE = "login";
        public static final String SEARCH_PAGE_USER = "searchPageUser";
        public static final String SEARCH_PAGE_ADMIN = "searchPageAdmin";
    }

    public class searchLastnameFeature {

        public static final String SEARCH_PAGE_ADMIN = "searchPageAdmin";
        public static final String SEARCH_PAGE_USER = "searchPageUser";
    }

    public class deleteAccountFeature {

        public static final String SEARCH_LATS_NAME_SERVLET = "searchAccountAction";
    }

    public class updateAccountFeature {

        public static final String SEARCH_LATS_NAME_SERVLET = "searchAccountAction";
    }

    public class addItemToCartFeature {

        public static final String SHOPPING_PAGE = "shop";
    }

    public class loadProductFeature {

        public static final String SHOPPING_PAGE = "shopPage";
    }

    public class logoutFeature {

        public static final String LOGIN_PAGE = "login";
    }

    public class registerFeature {

        public static final String LOGIN_PAGE = "login";
        public static final String ERROR_PAGE = "registerPage";
    }

    public class removeItemFeature {

        public static final String VIEW_CART_PAGE = "viewCart";
    }
    
    public class updateItemFeature{
    }
    public class checkoutFeature {

        public static final String SHOP_PAGE = "shop";
        public static final String VIEW_CART = "viewCart";
        public static final String CHECK_OUT = "checkout";
    }
    
    public class initParam{
        public static final String LOG4J = "LOG4J_PROPERTIES_FILE_LOCATION";
        public static final String SITEMAPS = "SITEMAPS_PROPERTIES_FILE_LOCATION";
        public static final String AUTHENTICATION = "AUTHENTICATION_PROPERTIES_FILE_LOCATION";
        public static final String ADMIN_AUTHOR = "ADMIN_AUTHORIZATION_PROPERTIES_FILE_LOCATION";
        public static final String USER_AUTHOR = "USER_AUTHORIZATION_PROPERTIES_FILE_LOCATION";
    }
   
}
