package com.wms.bean.component;

import java.util.ArrayList;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

import com.wms.bean.HasIdentity;
import com.wms.bean.enu.ComponentType;

@MappedSuperclass
public abstract class Component extends HasIdentity {
	
	@Column(name = "component_parent", nullable = true)
	protected Long parent; // Must be a global unique id such as snowflake
	
	@Column(name = "component_childs")
	protected ArrayList<Component> children;
	
	@Column(name = "component_type", nullable = false)
	protected ComponentType componentType;
	
	public Component() {
		super();
	}
	
	public Component(Long parent) {
		super();
		this.parent = parent;
	}
	
	public void put(Component component) {
		this.children.add(component);
	}
	
	public void remove(Component component) {
		this.children.remove(component);
	}

	public Long getParent() {
		return parent;
	}

	public Component setParent(Long parent) {
		this.parent = parent;
		return this;
	}

	public ArrayList<Component> getChildren() {
		return children;
	}

	public Component setChildren(ArrayList<Component> children) {
		this.children = children;
		return this;
	}

	public ComponentType getComponentType() {
		return componentType;
	}

	public Component setComponentType(ComponentType componentType) {
		this.componentType = componentType;
		return this;
	}
	
	
}
