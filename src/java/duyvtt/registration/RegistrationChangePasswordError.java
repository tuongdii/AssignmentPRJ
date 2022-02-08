/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package duyvtt.registration;

import java.io.Serializable;

/**
 *
 * @author DELL
 */
public class RegistrationChangePasswordError implements Serializable{
    private String wrongPassword;
    private String confirmNotMatch;
    private String passwordLengthErr;

    public RegistrationChangePasswordError() {
    }

    /**
     * @return the wrongPassword
     */
    public String getWrongPassword() {
        return wrongPassword;
    }

    /**
     * @param wrongPassword the wrongPassword to set
     */
    public void setWrongPassword(String wrongPassword) {
        this.wrongPassword = wrongPassword;
    }

    /**
     * @return the confirmNotMatch
     */
    public String getConfirmNotMatch() {
        return confirmNotMatch;
    }

    /**
     * @param confirmNotMatch the confirmNotMatch to set
     */
    public void setConfirmNotMatch(String confirmNotMatch) {
        this.confirmNotMatch = confirmNotMatch;
    }

    /**
     * @return the passwordLengthErr
     */
    public String getPasswordLengthErr() {
        return passwordLengthErr;
    }

    /**
     * @param passwordLengthErr the passwordLengthErr to set
     */
    public void setPasswordLengthErr(String passwordLengthErr) {
        this.passwordLengthErr = passwordLengthErr;
    }
    
}
