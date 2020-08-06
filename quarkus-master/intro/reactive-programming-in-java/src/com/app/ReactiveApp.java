package com.app;

import java.util.Iterator;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.Scheduler;
import io.reactivex.schedulers.Schedulers;

//----------------------------------------------------------

class DataList<E> implements Iterable<E> {
	@Override
	public Iterator<E> iterator() {
		return new Iterator<E>() {
			@Override
			public boolean hasNext() {
				return false;
			}

			@Override
			public E next() {
				return null;
			}
		};
	}
}

class DataLayer {

	public String getSingleItem() {
		// ...
		return "item-1";
	}

	public DataList<String> getMultipleItems() {
		return new DataList<String>();
	}

	public Future<String> getSingleItemAsync() {
		// ..
		ExecutorService executorService = Executors.newFixedThreadPool(1);
		return executorService.submit(() -> {
			return "item-1";
		});
	}

	public Observable<Object> getMultipleItemsAsync() {
		return Observable.create(subscriber -> {
			int i = 1;
			while (i <= 5) {
				TimeUnit.SECONDS.sleep(3);
				System.out.println(Thread.currentThread().getName() + " propagating new event");
				subscriber.onNext("item-" + i); // push
				i++;
			}
			subscriber.onComplete();
		}).observeOn(Schedulers.io());
	}

}



class ServiceLayer {

	DataLayer dataLayer = new DataLayer();

	public void doProcess() {

		// ....
		// dataLayer.getSingleItem(); // pull / sync / blocking-call

		// DataList<String> dataList = dataLayer.getMultipleItems();
		// Iterator<String> iterator = dataList.iterator();
		// while (iterator.hasNext()) {
		// String item = (String) iterator.next(); // pull / sync / blocking-call

		// }

		// Future<String> future = dataLayer.getSingleItemAsync();
		// try {
		// future.get();
		// } catch (InterruptedException | ExecutionException e) {
		// e.printStackTrace();
		// }

		Observable<Object> stream = dataLayer.getMultipleItemsAsync();
		stream
		.observeOn(Schedulers.computation())
		.subscribe(nextItem -> {
			System.out.println(Thread.currentThread().getName() + " reacting to an event");
			System.out.println(nextItem);
		}, err -> {
			System.out.println(err);
		}, () -> {
			System.out.println("completed");
		});

	}

}
//----------------------------------------------------------

public class ReactiveApp {

	public static void main(String[] args) {

		ServiceLayer serviceLayer = new ServiceLayer();
		serviceLayer.doProcess();

	}

}
