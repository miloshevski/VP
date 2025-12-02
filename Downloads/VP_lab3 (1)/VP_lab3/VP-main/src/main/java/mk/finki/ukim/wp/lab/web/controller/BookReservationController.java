package mk.finki.ukim.wp.lab.web.controller;

import jakarta.servlet.http.HttpServletRequest;
import mk.finki.ukim.wp.lab.model.BookReservation;
import mk.finki.ukim.wp.lab.service.BookReservationService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class BookReservationController {

    private final BookReservationService reservationService;

    public BookReservationController(BookReservationService reservationService) {
        this.reservationService = reservationService;
    }

    @PostMapping("/bookReservation")
    public String createReservation(@RequestParam Long bookId,
                                    @RequestParam Long numberOfCopies,
                                    @RequestParam String readerName,
                                    @RequestParam String readerAddress,
                                    HttpServletRequest request,
                                    Model model) {

        // IP адресата (ако сакаш да ја покажеш на confirmation страницата)
        String clientIp = request.getRemoteAddr();

        // креирај резервација преку сервис
        BookReservation reservation = reservationService.placeReservation(
                bookId, readerName, readerAddress, numberOfCopies
        );

        // додај во model за confirmation страната
        model.addAttribute("reservation", reservation);
        model.addAttribute("clientIp", clientIp);

        return "reservationConfirmation"; // reservationConfirmation.html
    }
}
