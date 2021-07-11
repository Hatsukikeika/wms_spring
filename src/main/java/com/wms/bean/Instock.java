package com.wms.bean;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.ConstraintMode;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "wmsgroup_item_instock")
public class Instock extends HasIdentity implements Serializable {

	@Column(name="instock_total", nullable = false, columnDefinition="int default '0'")
	private Integer total;
	
	@Column(name="instock_sold", nullable = false, columnDefinition="int default '0'")
	private Integer sold;
	
	@Column(name="instock_remain", nullable = false, columnDefinition="int default '0'")
	private Integer remain;
	
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "instock_item_openid", nullable = false, referencedColumnName = "data_global_id",
			foreignKey = @ForeignKey(name="none",value = ConstraintMode.NO_CONSTRAINT))
	private Item item;
	
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "instock_storage_openid", nullable = false, referencedColumnName = "data_global_id",
			foreignKey = @ForeignKey(name="none",value = ConstraintMode.NO_CONSTRAINT))
	private Storage storage;
	
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "instock_group_openid", nullable = false, referencedColumnName = "data_global_id",
			foreignKey = @ForeignKey(name="none",value = ConstraintMode.NO_CONSTRAINT))
	private Group group;
	
	public Instock() {
		super();
	}
	
	public Instock(Storage storage) {
		super();
		this.storage = storage;
	}

	public Instock(Item item, Storage storage) {
		super();
		this.total = 0;
		this.sold = 0;
		this.remain = 0;
		this.item = item;
		this.storage = storage;
	}

	public Integer getTotal() {
		return total;
	}

	public Instock setTotal(Integer total) {
		this.total = total;
		return this;
	}

	public Integer getSold() {
		return sold;
	}

	public Instock setSold(Integer sold) {
		this.sold = sold;
		return this;
	}

	public Integer getRemain() {
		return remain;
	}

	public Instock setRemain(Integer remain) {
		this.remain = remain;
		return this;
	}

	public Item getItem() {
		return item;
	}

	public Instock setItem(Item item) {
		this.item = item;
		return this;
	}

	public Storage getStorage() {
		return storage;
	}

	public Instock setStorage(Storage storage) {
		this.storage = storage;
		return this;
	}

	public Group getGroup() {
		return group;
	}

	public Instock setGroup(Group group) {
		this.group = group;
		return this;
	}

	
}
