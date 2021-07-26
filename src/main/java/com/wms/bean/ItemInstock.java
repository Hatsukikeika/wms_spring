package com.wms.bean;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "wmsitem")
@DiscriminatorValue("iteminstock")
public class ItemInstock extends Package {

}
