package model;

import java.util.Comparator;
import java.util.Date;

public class OrdineComparator implements Comparator<Ordine> {

	@Override
	public int compare(Ordine ordine1, Ordine ordine2) {
		Date data1 = ordine1.getData();
		Date data2 = ordine2.getData();

		return data1.compareTo(data2);
	}
}