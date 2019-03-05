package services;

import jadex.commons.future.Future;
import jadex.commons.future.IFuture;

public class SumService implements ISumService{
    public IFuture<Integer> addValues(int a, int b) {
        int sum = a + b;
        return new Future<Integer>(sum);
    }
}
