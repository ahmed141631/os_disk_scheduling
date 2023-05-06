import java.util.*;

public class DiskScheduler {
    private Queue<Integer> requestsQueue;
    private int startingPosition;

    public DiskScheduler(int[] requests, int startingPosition) {
        this.requestsQueue = new LinkedList<>();
        for (int i = 0; i < requests.length; i++) {
            this.requestsQueue.offer(requests[i]);
        }
        this.startingPosition = startingPosition;
    }

    public void FIFO() {
        int currentPosition = this.startingPosition;
        int totalSeekTime = 0;
        int numRequestsServiced = 0;
        while (!requestsQueue.isEmpty()) {
            int nextRequest = requestsQueue.poll();
            int seekTime = Math.abs(nextRequest - currentPosition);
            totalSeekTime += seekTime;
            numRequestsServiced++;
            System.out.println("Servicing request " + nextRequest + " (seek time: " + seekTime + ")");
            currentPosition = nextRequest;
        }
        double averageSeekTime = (double) totalSeekTime / numRequestsServiced;
        System.out.println("FIFO: Average seek time: " + averageSeekTime);
    }

    public void SCAN() {
        int currentPosition = this.startingPosition;
        int totalSeekTime = 0;
        int numRequestsServiced = 0;
        List<Integer> requestsList = new ArrayList<>(requestsQueue);
        Collections.sort(requestsList);
        int index = requestsList.indexOf(currentPosition);
        boolean direction = true; // true = right, false = left
        while (!requestsQueue.isEmpty()) {
            if (index == requestsList.size() - 1) {
                direction = false;
            } else if (index == 0) {
                direction = true;
            }
            int nextRequest = requestsList.get(index);
            int seekTime = Math.abs(nextRequest - currentPosition);
            totalSeekTime += seekTime;
            numRequestsServiced++;
            System.out.println("Servicing request " + nextRequest + " (seek time: " + seekTime + ")");
            currentPosition = nextRequest;
            requestsList.remove(index);
            if (direction) {
                index++;
            } else {
                index--;
            }
        }
        double averageSeekTime = (double) totalSeekTime / numRequestsServiced;
        System.out.println("SCAN: Average seek time: " + averageSeekTime);
    }

    public void SSTF() {
        int currentPosition = this.startingPosition;
        int totalSeekTime = 0;
        int numRequestsServiced = 0;
        while (!requestsQueue.isEmpty()) {
            int minSeekTime = Integer.MAX_VALUE;
            int nextRequest = -1;
            for (int request : requestsQueue) {
                int seekTime = Math.abs(request - currentPosition);
                if (seekTime < minSeekTime) {
                    minSeekTime = seekTime;
                    nextRequest = request;
                }
            }
            requestsQueue.remove(nextRequest);
            int seekTime = Math.abs(nextRequest - currentPosition);
            totalSeekTime += seekTime;
            numRequestsServiced++;
            System.out.println("Servicing request " + nextRequest + " (seek time: " + seekTime + ")");
            currentPosition = nextRequest;
        }
        double averageSeekTime = (double) totalSeekTime / numRequestsServiced;
        System.out.println("SSTF: Average seek time: " + averageSeekTime);
    }

    public void C_SCAN() {
        int currentPosition = this.startingPosition;
        int totalSeekTime = 0;
        int numRequestsServiced = 0;
        List<Integer> requestsList = new ArrayList<>(requestsQueue);
        requestsList.add(0);
        requestsList.add(requestsList.size(), 9999); // assuming max request is 9999
        Collections.sort(requestsList);
        int index = requestsList.indexOf(currentPosition);
        while (!requestsQueue.isEmpty()) {
            if (index == requestsList.size() - 1) {
                index = 0;
                currentPosition = 0;
            } else {
                index++;
            }
            int nextRequest = requestsList.get(index);
            int seekTime = Math.abs(nextRequest - currentPosition);
            totalSeekTime += seekTime;
            numRequestsServiced++;
            System.out.println("Servicing request " + nextRequest + " (seek time: " + seekTime + ")");
            currentPosition = nextRequest;
            requestsQueue.remove(nextRequest);
        }
        double averageSeekTime = (double) totalSeekTime / numRequestsServiced;
        System.out.println("C-SCAN: Average seek time: " + averageSeekTime);
    }
    public void F_SCAN() {
        int currentPosition = this.startingPosition;
        int totalSeekTime = 0;
        int numRequestsServiced = 0;
        List<Integer> requestsList = new ArrayList<>(requestsQueue);
        requestsList.add(0);
        requestsList.add(requestsList.size(), 9999); // assuming max request is 9999
        Collections.sort(requestsList);
        int index = requestsList.indexOf(currentPosition);
        boolean direction = true; // true = right, false = left
        while (!requestsQueue.isEmpty()) {
            if (index == requestsList.size() - 1 || index == 0) {
                direction = !direction;
            }
            if (direction) {
                index++;
            } else {
                index--;
            }
            int nextRequest = requestsList.get(index);
            int seekTime = Math.abs(nextRequest - currentPosition);
            totalSeekTime += seekTime;
            numRequestsServiced++;
            System.out.println("Servicing request " + nextRequest + " (seek time: " + seekTime + ")");
            currentPosition = nextRequest;
            requestsQueue.remove(nextRequest);
        }
        double averageSeekTime = (double) totalSeekTime / numRequestsServiced;
        System.out.println("F-SCAN: Average seek time: " + averageSeekTime);
    }
    public void N_SCAN() {
    int currentPosition = this.startingPosition;
    int totalSeekTime = 0;
    int numRequestsServiced = 0;
    List<Integer> requestsList = new ArrayList<>(requestsQueue);
    requestsList.add(0);
    requestsList.add(requestsList.size(), 9999); // assuming max request is 9999
    Collections.sort(requestsList);
    int index = requestsList.indexOf(currentPosition);
    boolean direction = true; // true = right, false = left
    int n = 5; // number of requests to service in one direction before switching
    int requestsServiced = 0;
    while (!requestsQueue.isEmpty()) {
        if (index == requestsList.size() - 1 || index == 0 || requestsServiced == n) {
            direction = !direction;
            requestsServiced = 0;
        }
        if (direction) {
            index++;
        } else {
            index--;
        }
        int nextRequest = requestsList.get(index);
        int seekTime = Math.abs(nextRequest - currentPosition);
        totalSeekTime += seekTime;
        numRequestsServiced++;
        System.out.println("Servicing request " + nextRequest + " (seek time: " + seekTime + ")");
        currentPosition = nextRequest;
        requestsQueue.remove(nextRequest);
        requestsServiced++;
    }
    double averageSeekTime = (double) totalSeekTime / numRequestsServiced;
    System.out.println("N-SCAN: Average seek time: " + averageSeekTime);
}

    
}
