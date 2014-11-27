/**
 * Created by sergey on 26.11.14.
 */

import java.util.*;

public class Solution {
    public static void main(String[] args) {

    }

    static String[] graph(String[] input) {
        Map<String, Node> allNodes = new HashMap<String, Node>();

        for (int i = 0; i < input.length; i++) {
            String s = input[i];
            boolean lastElement = i == (input.length - 1);
            if (lastElement) {
                return traverseGraph(allNodes, s);
            }
            String[] nodeFriends = s.split(":");
            if (nodeFriends.length > 1) {
                String nodeName = nodeFriends[0];
                Node node = allNodes.get(nodeName);
                if (node == null) {
                    node = new Node(nodeName);
                }
                String friendsStr = nodeFriends[1];
                String[] friends = friendsStr.split(",");
                for (String friendName : friends) {
                    node.addFriend(friendName);
                }
                allNodes.put(nodeName, node);
            } else {
                allNodes.put(nodeFriends[0], new Node(nodeFriends[0]));
            }
        }
        return null;
    }

    private static String[] traverseGraph(Map<String, Node> allNodes, String rootNode) {
        Queue<KeyValue> mapResult = map(rootNode, allNodes);
        ArrayList<String> result = new ArrayList<String>();
        int currentLevel = 1;
        List<String> childRow = new ArrayList<String>();
        for (KeyValue entry : mapResult) {
            if (currentLevel == entry.getLevel()) {
                childRow.add(entry.getNode());
            } else {
                currentLevel++;
                result.add(join(childRow));
                childRow = new ArrayList<String>();
                childRow.add(entry.getNode());
            }
        }
        result.add(join(childRow));
        return result.toArray(new String[result.size()]);
    }

    private static Queue<KeyValue> map(String rootNode, Map<String, Node> allNodes) {
        Set<String> traversed = new HashSet<String>();
        PriorityQueue<KeyValue> mapResult = new PriorityQueue<KeyValue>();
        Queue<String> queue = new LinkedList<String>();
        queue.add(rootNode);
        int level = 1;
        while (!queue.isEmpty()) {
            Node node = allNodes.get(queue.poll());
            if (node == null) {
                continue;
            }
            traversed.add(node.name);
            for (String friend : node.friends) {
                if (traversed.contains(friend)) {
                    continue;
                }
                mapResult.add(new KeyValue(level, friend));
                queue.add(friend);
                traversed.add(friend);
            }
            level++;
        }
        return mapResult;
    }

    private static String join(List<String> friends) {
        String result = "";
        for (int i = 0; i < friends.size(); i++) {
            String node = friends.get(i);
            result += node;
            if (i < friends.size() - 1) {
                result += ",";
            }
        }
        return result;
    }

    static class Node {
        private String name;
        private List<String> friends = new ArrayList<String>();

        public Node(String name) {
            this.name = name;
        }

        public void addFriend(String friend) {
            friends.add(friend);
        }
    }

    static class KeyValue implements Comparable<KeyValue> {
        private int level;
        private String node;

        @Override
        public int compareTo(KeyValue o) {
            return level - o.level;
        }

        KeyValue(int level, String node) {
            this.level = level;
            this.node = node;
        }

        public int getLevel() {
            return level;
        }

        public String getNode() {
            return node;
        }
    }
}

