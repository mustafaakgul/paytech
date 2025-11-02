package org.fintech.paytech.domain.core.tracker.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import org.fintech.paytech.domain.core.asset.model.Asset;
import org.fintech.paytech.model.BaseDeletableModel;

@Entity
@Table(name = "trackers")
public class Tracker extends BaseDeletableModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "asset_id")
    private Asset asset;
}
