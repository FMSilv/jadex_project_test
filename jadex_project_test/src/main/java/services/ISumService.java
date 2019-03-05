package services;

import jadex.commons.future.IFuture;

public interface ISumService {
	public IFuture<Integer> addValues(int a, int b);
}
