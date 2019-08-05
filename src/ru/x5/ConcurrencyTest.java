package ru.x5;

public class ConcurrencyTest {

  public static void main(String[] args) {
    Store store = new Store();
    new Producer(store).start();
    new Consumer(store).start();
  }

  /**
   * Магазин. Хранит товары: можно положить, можно забрать
   */
  private static class Store {
    int goods;
    Object lock = new Object();

    public void get() throws InterruptedException {
      synchronized (lock) {
        while (goods < 1) {
          lock.wait();
        }
        --goods;
        System.out.println("Забрали товар. Осталось " + goods);
        notifyAll();
      }
    }

    public void put()  throws InterruptedException {
      synchronized (lock) {
        while (goods >= 3) {
          lock.wait();
        }
        ++goods;
        System.out.println("Приняли товар. Товаров " + goods);
        notifyAll();
      }
    }
  }

  /**
   * Производит товары и кладёт их в магазин
   */
  private static class Producer extends Thread {
    private Store store;

    public Producer(Store store) {
      this.store = store;
    }

    @Override
    public void run() {
      int  i = 6;
      while (i > 0) {
        try {
          store.put();
          --i;
        } catch (InterruptedException e) {
          e.printStackTrace();
          return;
        }
      }
    }
  }

  /**
   * Забирает товары из магазина
   */
  private static class Consumer extends Thread {
    private Store store;

    public Consumer(Store store) {
      this.store = store;
    }

    @Override
    public void run() {
      int  i = 6;
      while (i > 0) {
        try {
          store.get();
          --i;
        } catch (InterruptedException e) {
          e.printStackTrace();
          return;
        }
      }
    }
  }
}
