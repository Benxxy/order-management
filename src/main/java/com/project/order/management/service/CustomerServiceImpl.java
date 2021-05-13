package com.project.order.management.service;

import com.project.order.management.config.JwtTokenUtil;
import com.project.order.management.data.CustomerDTO;
import com.project.order.management.data.entity.Customer;
import com.project.order.management.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenUtil jwtTokenUtil;
    private final UserDetailsService userDetailsService;

    @Override
    public String createCustomer(CustomerDTO customerDTO) {
        Customer customer = customerRepository.findByUsername(customerDTO.getUsername());
        if (customer != null) {
            throw new IllegalArgumentException("The user already exists.");
        }
        Customer newCustomer = new Customer();
        newCustomer.setFirstName(customerDTO.getFirstName());
        newCustomer.setLastName(customerDTO.getLastName());
        newCustomer.setEmail(customerDTO.getEmail());
        newCustomer.setUsername(customerDTO.getUsername());
        newCustomer.setPassword(passwordEncoder.encode(customerDTO.getPassword()));

        newCustomer = customerRepository.saveAndFlush(newCustomer);

        final UserDetails userDetails = userDetailsService
                .loadUserByUsername(newCustomer.getUsername());

        return jwtTokenUtil.generateToken(userDetails, newCustomer.getId());
    }
}
