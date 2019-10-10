package com.test.events.model;

import lombok.Data;
import lombok.NoArgsConstructor;


import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;

/**
 * Create a class Event that will be our table in database.
 *
 * @author Shakhov Yevhen
 */
@Entity
@Table
@Data
@NoArgsConstructor
public class Event implements Serializable {
    /*
    Create class fields, which in the future will be columns in the table.
    */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(unique = true, nullable = false)
    private String name;
    @Column(name = "event_type", nullable = false)
    private String eventType;
    @Column
    private LocalDate date;

}
