package com.raven.model;

import javax.swing.Icon;
import javax.swing.ImageIcon;

public class Model_Menu {

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public MenuType getType() {
        return type;
    }

    public void setType(MenuType type) {
        this.type = type;
    }

    public Model_Menu(String id, String icon, String name, MenuType type, Model_Menu... subMenu) {
        this.id = id;
        this.icon = icon;
        this.name = name;
        this.type = type;
        this.subMenu = subMenu;
    }
    public Model_Menu(String id, String icon, String name, MenuType type) {
        this.id = id;
        this.icon = icon;
        this.name = name;
        this.type = type;
    }
    public Model_Menu() {
    }
    
    public Model_Menu[] getSubMenu(){
        return subMenu;
    }
    
    public boolean hasSubMenu() {
        return subMenu != null && subMenu.length > 0;
    }
    
    private boolean isOpen;

    public boolean isOpen() {
        return isOpen;
    }

    public void setOpen(boolean open) {
        this.isOpen = open;
    }
    
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
    
    private String icon;
    private String name;
    private MenuType type;
    private String id;
    private Model_Menu[] subMenu;
    public Icon toIcon() {
        return new ImageIcon(getClass().getResource("/com/raven/icon/" + icon + ".png"));
    }

    public static enum MenuType {
        TITLE, MENU, EMPTY
    }
}
