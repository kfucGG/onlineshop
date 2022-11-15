package com.example.onlineshopping.security;

import com.example.onlineshopping.models.Buyer;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public class BuyerDetails implements UserDetails {

    private final Buyer buyer;

    public BuyerDetails(Buyer buyer) {
        this.buyer = buyer;
    }

    //TODO
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singletonList(new SimpleGrantedAuthority(buyer.getRole()));
    }

    @Override
    public String getPassword() {
        return buyer.getPassword();
    }

    @Override
    public String getUsername() {
        return buyer.getUsername();
    }


    //TODO
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
