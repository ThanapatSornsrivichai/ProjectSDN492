/**
 *    Copyright 2013, Big Switch Networks, Inc.
 *
 *    Licensed under the Apache License, Version 2.0 (the "License"); you may
 *    not use this file except in compliance with the License. You may obtain
 *    a copy of the License at
 *
 *         http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 *    WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 *    License for the specific language governing permissions and limitations
 *    under the License.
 **/

package net.floodlightcontroller.topology;

import net.floodlightcontroller.linkdiscovery.Link;
import org.projectfloodlight.openflow.types.DatapathId;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Cluster {
    protected DatapathId id; // the lowest id of the nodes
    protected Map<DatapathId, Set<Link>> links; // set of links connected to a node.

    public Cluster() {
        id = DatapathId.NONE;
        links = new HashMap<DatapathId, Set<Link>>();
    }

    public DatapathId getId() {
        return id;
    }

    public void setId(DatapathId id) {
        this.id = id;
    }

    public Map<DatapathId, Set<Link>> getLinks() {
        return links;
    }

    public Set<DatapathId> getNodes() {
        return links.keySet();
    }

    void add(DatapathId n) {
        if (links.containsKey(n) == false) {
            links.put(n, new HashSet<Link>());
			if (id == DatapathId.NONE || n.getLong() < id.getLong()) 
				id = n ;
        }
    }

    void addLink(Link l) {
        add(l.getSrc());
        links.get(l.getSrc()).add(l);

        add(l.getDst());
        links.get(l.getDst()).add(l);
     }
    

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Cluster cluster = (Cluster) o;

        return id != null ? id.equals(cluster.id) : cluster.id == null;
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }

    public String toString() {
        return "[Cluster id=" + id.toString() + ", " + links.keySet() + "]";
    }
}
