package com.wms.bean;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "wmsisitem")
@DiscriminatorValue("iteminstock")
public class InstockItem extends Package {

}
