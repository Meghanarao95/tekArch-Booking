package com.acme.springbootbootstrapproject.controller;

import com.acme.springbootbootstrapproject.entity.Bookings;
import com.acme.springbootbootstrapproject.services.TafBookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/bookings")
public class TafBookingController {

    @Autowired
    private TafBookingService tafBookingService;

    // Create a new booking
    @PostMapping
    public ResponseEntity<Bookings> createBooking(@RequestParam Long userId, @RequestParam Long flightId) {
        try {
            Bookings booking = tafBookingService.createBooking(userId, flightId);
            return ResponseEntity.ok(booking);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    // Get booking details
    @GetMapping("/{bookingId}")
    public ResponseEntity<Bookings> getBookingById(@PathVariable Long bookingId) {
        Optional<Bookings> booking = tafBookingService.getBookingById(bookingId);
        return booking.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Get all bookings for a user
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Bookings>> getBookingsByUser(@PathVariable Long userId) {
        List<Bookings> bookings = tafBookingService.getBookingsByUser(userId);
        return ResponseEntity.ok(bookings);
    }

    // Cancel a booking
    @DeleteMapping("/{bookingId}")
    public ResponseEntity<Bookings> cancelBooking(@PathVariable Long bookingId) {
        try {
            Bookings booking = tafBookingService.cancelBooking(bookingId);
            return ResponseEntity.ok(booking);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
