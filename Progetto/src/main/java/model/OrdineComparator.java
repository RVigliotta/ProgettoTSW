package model;

import java.util.Comparator;

public class OrdineComparator implements Comparator<Ordine> {

	@Override
	public int compare(Ordine ordine1, Ordine ordine2) {
        return ordine1.getData().compareTo(ordine2.getData());
    }
}