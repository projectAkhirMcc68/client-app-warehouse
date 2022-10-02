/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mii.co.id.clientappwarehouse.util;

import java.nio.charset.Charset;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.http.HttpHeaders;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

/**
 *
 * @author USER
 */
@Component
public class createHeaders {
    
    
    public static String makeBasicAuth(String username,String password){
                String auth = username + ":" + password;
                byte[] encode =Base64.encodeBase64(auth.getBytes(Charset.forName("US-ASCII")));
                return new String(encode);
        }
    
    public static HttpHeaders setHeaders(){
       Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return new HttpHeaders(){{
                set("Authorization","Basic  "+ auth.getCredentials());
        }
    };
                }
    
    
}
