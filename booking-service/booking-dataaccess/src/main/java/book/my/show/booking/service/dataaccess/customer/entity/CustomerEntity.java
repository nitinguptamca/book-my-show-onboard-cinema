package book.my.show.booking.service.dataaccess.customer.entity;

import jakarta.persistence.Entity;

import jakarta.persistence.Table;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;


@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "booking_customer_m_view", schema = "customer")
@Entity
public class CustomerEntity {

    @Id
    private UUID id;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }
}
