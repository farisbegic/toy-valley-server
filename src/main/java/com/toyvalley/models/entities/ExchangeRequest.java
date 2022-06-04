package com.toyvalley.models.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "exchange_request")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ExchangeRequest {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    private Toy toy_offered;

    @ManyToOne
    private Toy toy_requested;

    @Column(name = "active")
    private boolean isActive;

    @Column(name = "message")
    private String message;

    @Column(name = "request_date")
    private Date requestDate;

    @Column(name = "accept_date")
    private Date acceptDate;

    public ExchangeRequest(Toy toy_offered, Toy toy_requested, String message) {
        this.toy_offered = toy_offered;
        this.toy_requested = toy_requested;
        this.isActive = true;
        this.message = message;
        this.requestDate = new Date();
        this.acceptDate = new Date();
    }
}
