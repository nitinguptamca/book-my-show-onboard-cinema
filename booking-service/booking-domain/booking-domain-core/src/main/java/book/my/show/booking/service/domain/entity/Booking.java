package book.my.show.booking.service.domain.entity;

import book.my.show.booking.service.domain.exception.BookingDomainException;
import book.my.show.booking.service.domain.valueobject.BookingId;
import book.my.show.booking.service.domain.valueobject.BookingStatus;
import book.my.show.booking.service.domain.valueobject.CinemaId;
import book.my.show.booking.service.domain.valueobject.CustomerId;
import book.my.show.booking.service.domain.valueobject.Money;
import book.my.show.booking.service.domain.valueobject.BookingItemId;
import book.my.show.booking.service.domain.valueobject.TrackingId;


import java.util.List;
import java.util.UUID;

public class Booking extends AggregateRoot<BookingId> {
    private final CustomerId customerId;
    private final CinemaId cinemaId ;
    private final Money price;
    private final List<BookingItems> items;

    private TrackingId trackingId;
    private BookingStatus bookingStatus;
    private List<String> failureMessages;

    public static final String FAILURE_MESSAGE_DELIMITER = ",";

    public void initializeBooking() {
        setId(new BookingId(UUID.randomUUID()));
        trackingId = new TrackingId(UUID.randomUUID());
        bookingStatus = BookingStatus.PENDING;
        initializeBookingItems();
    }

    public void validateBooking() {
        validateInitialBooking();
        validateTotalPrice();
        validateItemsPrice();
    }

    public void pay() {
        if (bookingStatus != BookingStatus.PENDING) {
            throw new BookingDomainException("Booking is not in correct state for pay operation!");
        }
        bookingStatus = BookingStatus.PAID;
    }

    public void approve() {
        if(bookingStatus != BookingStatus.PAID) {
            throw new BookingDomainException("Booking is not in correct state for approve operation!");
        }
        bookingStatus = BookingStatus.APPROVED;
    }

    public void initCancel(List<String> failureMessages) {
        if (bookingStatus != BookingStatus.PAID) {
            throw new BookingDomainException("Booking is not in correct state for initCancel operation!");
        }
        bookingStatus = BookingStatus.CANCELLING;
        updateFailureMessages(failureMessages);
    }

    public void cancel(List<String> failureMessages) {
        if (!(bookingStatus == BookingStatus.CANCELLING || bookingStatus == BookingStatus.PENDING)) {
            throw new BookingDomainException("Booking is not in correct state for cancel operation!");
        }
        bookingStatus = BookingStatus.CANCELLED;
        updateFailureMessages(failureMessages);
    }

    private void updateFailureMessages(List<String> failureMessages) {
        if (this.failureMessages != null && failureMessages != null) {
            this.failureMessages.addAll(failureMessages.stream().filter(message -> !message.isEmpty()).toList());
        }
        if (this.failureMessages == null) {
            this.failureMessages = failureMessages;
        }
    }

    private void validateInitialBooking() {
        if (bookingStatus != null || getId() != null) {
            throw new BookingDomainException("Booking is not in correct state for initialization!");
        }
    }

    private void validateTotalPrice() {
        if (price == null || !price.isGreaterThanZero()) {
            throw new BookingDomainException("Total price must be greater than zero!");
        }
    }

    private void validateItemsPrice() {
        Money BookingItemsTotal = items.stream().map(bookingItems -> {
            validateItemPrice(bookingItems);
            return bookingItems.getSubTotal();
        }).reduce(Money.ZERO, Money::add);

        if (!price.equals(BookingItemsTotal)) {
            throw new BookingDomainException("Total price: " + price.getAmount()
                + " is not equal to Booking items total: " + BookingItemsTotal.getAmount() + "!");
        }
    }

    private void validateItemPrice(BookingItems bookingItems) {
        if (!bookingItems.isPriceValid()) {
            throw new BookingDomainException("Booking item price: " + bookingItems.getPrice().getAmount() +
                    " is not valid for movie " + bookingItems.getMovie().getId().getValue());
        }
    }

    private void initializeBookingItems() {
        long itemId = 1;
        for (BookingItems bookingItems : items) {
            bookingItems.initializeBookingItem(super.getId(), new BookingItemId(itemId++));
        }
    }

    private Booking(Builder builder) {
        super.setId(builder.BookingId);
        customerId = builder.customerId;
        cinemaId = builder.cinemaId;
        price = builder.price;
        items = builder.items;
        trackingId = builder.trackingId;
        bookingStatus = builder.BookingStatus;
        failureMessages = builder.failureMessages;
    }

    public static Builder builder() {
        return new Builder();
    }

    public CustomerId getCustomerId() {
        return customerId;
    }

    public CinemaId getcinemaId() {
        return cinemaId;
    }

    public Money getPrice() {
        return price;
    }

    public List<BookingItems> getItems() {
        return items;
    }

    public TrackingId getTrackingId() {
        return trackingId;
    }

    public BookingStatus getBookingStatus() {
        return bookingStatus;
    }

    public List<String> getFailureMessages() {
        return failureMessages;
    }

    public static final class Builder {
        private BookingId BookingId;
        private CustomerId customerId;
        private CinemaId cinemaId;
        private Money price;
        private List<BookingItems> items;
        private TrackingId trackingId;
        private BookingStatus BookingStatus;
        private List<String> failureMessages;

        private Builder() {
        }

        public Builder BookingId(BookingId val) {
            BookingId = val;
            return this;
        }

        public Builder customerId(CustomerId val) {
            customerId = val;
            return this;
        }

        public Builder cinemaId(CinemaId val) {
            cinemaId = val;
            return this;
        }


        public Builder price(Money val) {
            price = val;
            return this;
        }

        public Builder items(List<BookingItems> val) {
            items = val;
            return this;
        }

        public Builder trackingId(TrackingId val) {
            trackingId = val;
            return this;
        }

        public Builder BookingStatus(BookingStatus val) {
            BookingStatus = val;
            return this;
        }

        public Builder failureMessages(List<String> val) {
            failureMessages = val;
            return this;
        }

        public Booking build() {
            return new Booking(this);
        }
    }
}
