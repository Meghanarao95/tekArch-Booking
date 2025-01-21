package com.acme.springbootbootstrapproject.services;

import com.acme.springbootbootstrapproject.entity.Bookings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;

@Service
public class TafBookingService {

    @Autowired
    private RestTemplate restTemplate;

    private final String BASE_URL = "http://localhost:9200/datastore";

    // Create a booking
    public Bookings createBooking(Long userId, Long flightId) {
        String url = BASE_URL + "/bookings/" + userId + "/" + flightId;

        ResponseEntity<Bookings> response = restTemplate.exchange(url,
                org.springframework.http.HttpMethod.POST,
                null,
                Bookings.class);

        return response.getBody();
    }

    // Get booking details
    public Optional<Bookings> getBookingById(Long bookingId) {
        String url = BASE_URL + "/bookings/" + bookingId;

        ResponseEntity<Bookings> response = restTemplate.exchange(url,
                org.springframework.http.HttpMethod.GET,
                null,
                Bookings.class);

        return Optional.ofNullable(response.getBody());
    }

    // Get all bookings for a user
    public List<Bookings> getBookingsByUser(Long userId) {
        String url = BASE_URL + "/bookings/user/" + userId;

        ResponseEntity<List<Bookings>> response = restTemplate.exchange(url,
                org.springframework.http.HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<Bookings>>() {});

        return response.getBody();
    }

    // Cancel a booking
    public Bookings cancelBooking(Long bookingId) {
        String url = BASE_URL + "/bookings/" + bookingId;

        ResponseEntity<Bookings> response = restTemplate.exchange(url,
                org.springframework.http.HttpMethod.DELETE,
                null,
                Bookings.class);

        // The response body will contain the deleted booking object
        return response.getBody();
    }
}
