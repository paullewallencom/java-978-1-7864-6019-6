/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.steeplesoft.photobeans.main.factories;

import com.steeplesoft.photobeans.main.nodes.MonthNode;
import com.steeplesoft.photobeans.manager.PhotoManager;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.openide.LifecycleManager;
import org.openide.nodes.ChildFactory;
import org.openide.nodes.Node;
import org.openide.util.Lookup;

/**
 *
 * @author jason
 */
public class MonthNodeFactory extends ChildFactory<String> {

    private final PhotoManager photoManager;
    private static final Logger LOGGER = Logger.getLogger(YearChildFactory.class.getName());
    private final int year;
    public MonthNodeFactory(int year) {
        this.year = year;
        this.photoManager = Lookup.getDefault().lookup(PhotoManager.class);
        if (photoManager == null) {
            LOGGER.log(Level.SEVERE, "Cannot get PhotoManager object");
            LifecycleManager.getDefault().exit();
        }
    }

    @Override
    protected boolean createKeys(List<String> toPopulate) {
        toPopulate.addAll(photoManager.getMonths(year));
        return true;
    }

    @Override
    protected Node createNodeForKey(String key) {
        return new MonthNode(year, Integer.parseInt(key));
    }
}
