package ru.practicum.statistics.datamodel;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class HitAnalyticEntity {

    @Id
    @Column(name = "id")
    private Integer id;
    @Column(name = "app", nullable = false)
    private String app;

    @Column(name = "uri", nullable = false)
    private String uri;

    @Column(name = "count")
    private Long count;

}
