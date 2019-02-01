package fr.aston.guide.models.hotel;

import java.io.Serializable;
import java.util.List;

public class Hotel implements Serializable {

    public List<Records> records;
    public List<FacetGroups> facet_groups;

}
