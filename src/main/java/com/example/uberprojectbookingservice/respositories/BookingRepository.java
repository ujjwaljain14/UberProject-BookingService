package com.example.uberprojectbookingservice.respositories;

import com.example.uberprojectentityservice.models.Booking;
import com.example.uberprojectentityservice.models.BookingStatus;
import com.example.uberprojectentityservice.models.Driver;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Long> {

//    public Booking updateBookingStatusById(BookingStatus bookingStatus, Long id);

    @Modifying
    @Transactional
    @Query("Update Booking b set b.bookingStatus= :status, b.driver= :driver WHERE b.id = :id ")
    void updateBookingStatusById(@Param("id")Long id, @Param("status") BookingStatus status,@Param("driver") Optional<Driver> driver);
}
