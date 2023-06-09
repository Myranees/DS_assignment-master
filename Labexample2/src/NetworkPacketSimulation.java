import java.util.*;

class Packet {
    private String packetType;
    private int priority;

    public Packet(String packetType, int priority) {
        this.packetType = packetType;
        this.priority = priority;
    }

    public int getPriority() {
        return priority;
    }

    public String toString() {
        return packetType + " (Priority=" + priority + ")";
    }
}

class PriorityQueue {
    private List<Packet> packets;

    public PriorityQueue() {
        packets = new ArrayList<>();
    }

    public void enqueue(Packet packet) {
        packets.add(packet);
        packets.sort(Comparator.comparingInt(Packet::getPriority).reversed());
    }

    public Packet dequeue() {
        if (isEmpty()) {
            return null;
        }
        return packets.remove(0);
    }

    public boolean isEmpty() {
        return packets.isEmpty();
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("10 packets arrived\n");
        for (Packet packet : packets) {
            sb.append(packet.toString()).append("\n");
        }
        return sb.toString();
    }
}

public class NetworkPacketSimulation {
    public static void main(String[] args) {
        List<Packet> packets = new ArrayList<>();
        packets.add(new Packet("Video 1", 1));
        packets.add(new Packet("Voice 2", 2));
        packets.add(new Packet("Data 3", 0));
        packets.add(new Packet("Data 4", 0));
        packets.add(new Packet("Voice 5", 2));
        packets.add(new Packet("Video 6", 1));
        packets.add(new Packet("Voice 7", 2));
        packets.add(new Packet("Voice 8", 2));
        packets.add(new Packet("Data 9", 0));
        packets.add(new Packet("Video 10", 1));

        System.out.println("10 packets arrived");
        for (Packet packet : packets) {
            System.out.println(packet.toString());
        }
        System.out.println();

        PriorityQueue queue = new PriorityQueue();

        // Enqueue packets
        for (Packet packet : packets) {
            queue.enqueue(packet);
        }

        // Process packets
        System.out.println("Processing 10 network packets");
        while (!queue.isEmpty()) {
            Packet packet = queue.dequeue();
            System.out.println(packet.toString());
        }
    }
}


