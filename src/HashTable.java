import java.util.ArrayList;
import java.util.LinkedList;

public class HashTable {
    private ArrayList<LinkedList<HashEntry>> buckets;

    private class HashEntry {
        String key;
        Object value;

        HashEntry(String key, Object value) {
            this.key = key;
            this.value = value;
        }

        @Override
        public String toString() {
            return "{" +
                    "'" + key + '\'' +
                    ", " + value +
                    '}';
        }
    }

    public HashTable(int bucketsNumber) {
        buckets = new ArrayList<>(bucketsNumber);
        for (int i = 0; i < bucketsNumber; i++) {
            buckets.add(new LinkedList<>());
        }
    }

    private int hashcode(String key) {
        return Math.abs(key.hashCode() % buckets.size());
    }

    public void put(String key, Object value) {
        int index = hashcode(key);
        LinkedList<HashEntry> bucket = buckets.get(index);
        for (HashEntry entry : bucket) {
            if (entry.key.equals(key)) {
                entry.value = value;
                return;
            }
        }
        bucket.add(new HashEntry(key, value));
    }

    public Object get(String key) {
        int index = hashcode(key);
        LinkedList<HashEntry> bucket = buckets.get(index);
        for (HashEntry entry : bucket) {
            if (entry.key.equals(key)) {
                return entry.value;
            }
        }
        return -1;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < buckets.size(); i++) {
            sb.append("Bucket ").append(i).append(": ");
            LinkedList<HashEntry> bucket = buckets.get(i);
            for (HashEntry entry : bucket) {
                sb.append(entry).append(" ");
            }
            sb.append("\n");
        }
        return sb.toString();
    }


    public static void main(String[] args) {
        HashTable hashTable = new HashTable(10);

        // Putting 20 Mandarin words into the hash table with their English equivalents
        hashTable.put("一", "one");
        hashTable.put("二", "two");
        hashTable.put("三", "three");
        hashTable.put("四", "four");
        hashTable.put("五", "five");
        hashTable.put("六", "six");
        hashTable.put("七", "seven");
        hashTable.put("八", "eight");
        hashTable.put("九", "nine");
        hashTable.put("十", "ten");
        hashTable.put("你好", "hello");
        hashTable.put("再见", "goodbye");
        hashTable.put("请", "please");
        hashTable.put("谢谢", "thank you");
        hashTable.put("是", "yes");
        hashTable.put("不", "no");
        hashTable.put("可以", "can");
        hashTable.put("好", "good");
        hashTable.put("去", "go");
        hashTable.put("来", "come");

        System.out.println("一 -> " + hashTable.get("一"));
        System.out.println("二 -> " + hashTable.get("二"));
        System.out.println("十 -> " + hashTable.get("十"));
        System.out.println("Not Existed -> " + hashTable.get("#")); // Key not in the table

        // Print hash table
        System.out.println(hashTable);
    }
}
