package com.akartkam.domain;


import java.io.Serializable;
import java.util.*;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
//import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import javax.persistence.OneToMany;
import javax.persistence.JoinColumn;
/**
 * An item for auction.
 *
 * @author Christian Bauer
 */
@Entity
@Table(name = "ITEM")
public class Item implements Serializable, Comparable {

	@Id
	@GeneratedValue
	@Column(name = "ITEM_ID")
	private Long id = null;
    private int version = 1;

    @Column(name = "NAME")
    private String name;
    
    @OneToMany(mappedBy = "item")
    @Cascade({CascadeType.SAVE_UPDATE, CascadeType.DELETE})
    private List<Bid> bids = new ArrayList<Bid>();

    @ManyToOne(optional=true)
    @JoinTable(
    		name = "ITEM_BUYER",
    		joinColumns = {@JoinColumn(name = "ITEM_ID")},
    		inverseJoinColumns = {@JoinColumn(name = "USER_ID")}
    )
    private User buyer;

    @ManyToMany(mappedBy = "items")
    private List<Category> categories = new ArrayList<Category>();
    
    public List<Category> getCategories() {
		return categories; //Collections.unmodifiableSet(categories);
	}
    
	public User getBuyer() {
		return buyer;
	}

	public void setBuyer(User buyer) {
		this.buyer = buyer;
	}

	public void setName(String name) {
		this.name = name;
	}

	/**
     * No-arg constructor for JavaBean tools.
     */
    public Item() {}

    /**
     * Full constructor
     
    public Item(String name, User seller, User buyer, String description,
                MonetaryAmount initialPrice, MonetaryAmount reservePrice, Date startDate, Date endDate,
                ItemState state, User approvedBy, Date approvalDatetime,
                List<Bid> bids, Bid successfulBid, Map<Long, Bid> bidsByIdentifier,
                Set<Category> categories, Set<CategorizedItem> categorizedItems,
                Collection<String> images) {
        this.name = name;
        this.seller = seller;
        this.buyer = buyer;
        this.description = description;
        this.initialPrice = initialPrice;
        this.reservePrice = reservePrice;
        this.startDate = startDate;
        this.endDate = endDate;
        this.state = state;
        this.approvedBy = approvedBy;
        this.approvalDatetime = approvalDatetime;
        this.bids = bids;
        this.successfulBid = successfulBid;
        this.bidsByIdentifier = bidsByIdentifier;
        this.categories = categories;
        this.categorizedItems = categorizedItems;
        this.images = images;

        // Referential integrity
        seller.getItemsForSale().add(this);
    } 

    /**
     * Simple constructors
     
    public Item(String name, String description, User seller,
                MonetaryAmount initialPrice, MonetaryAmount reservePrice,
                Date startDate, Date endDate) {
        this.name = name;
        this.seller = seller;
        this.description = description;
        this.initialPrice = initialPrice;
        this.reservePrice = reservePrice;
        this.startDate = startDate;
        this.endDate = endDate;
        this.state = ItemState.DRAFT;

        // Referential integrity
        seller.getItemsForSale().add(this);
    }*/
    
    public Item(String name) {
    	this.name = name;
    }

    // ********************** Accessor Methods ********************** //

    public Long getId() { return id; }


    public String getName() { return name; }





    public List<Bid> getBids() { return bids; }
    public void addBid(Bid bid) {
        if (bid == null)
            throw new IllegalArgumentException("Can't add a null Bid.");
        
        bid.setItem(this);
        this.getBids().add(bid);
       
        // Don't have to set the "other" side, a Bid can only be instantiated with a given item
    }

 
   
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Item)) return false;

        final Item item = (Item) o;

        if (name != null ? !name.equals(item.name) : item.name != null) return false;

        return true;
    }

    public int hashCode() {
        int result;
        result = (name != null ? name.hashCode() : 0);
        result = 29 * result;
        return result;
    }
/*
    public String toString() {
        return  "Item ('" + getId() + "'), " +
                "Name: '" + getName() + "' ";
    }
*/
    public int compareTo(Object o) {
        if (o instanceof Item) {
            // Don't compare Date objects! Use the time in milliseconds!
            return 1;
        }
        return 0;
    }

    // ********************** Business Methods ********************** //
    
    /**
     * Places a bid while checking business constraints.
     *
     * This method may throw a BusinessException if one of the requirements
     * for the bid placement wasn't met, e.g. if the auction already ended.
     *
     * @param bidder
     * @param bidAmount
     * @param currentMaxBid  the most valuable bid for this item
     * @param currentMinBid  the least valuable bid for this item
     * @return
     * @throws BusinessException
     */
   
}
