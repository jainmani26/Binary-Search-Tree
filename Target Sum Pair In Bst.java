import java.io.*;
import java.util.*;

public class Main {
  public static class Node {
    int data;
    Node left;
    Node right;

    Node(int data, Node left, Node right) {
      this.data = data;
      this.left = left;
      this.right = right;
    }
  }

  public static class Pair {
    Node node;
    int state;

    Pair(Node node, int state) {
      this.node = node;
      this.state = state;
    }
  }

  public static Node construct(Integer[] arr) {
    Node root = new Node(arr[0], null, null);
    Pair rtp = new Pair(root, 1);

    Stack<Pair> st = new Stack<>();
    st.push(rtp);

    int idx = 0;
    while (st.size() > 0) {
      Pair top = st.peek();
      if (top.state == 1) {
        idx++;
        if (arr[idx] != null) {
          top.node.left = new Node(arr[idx], null, null);
          Pair lp = new Pair(top.node.left, 1);
          st.push(lp);
        } else {
          top.node.left = null;
        }

        top.state++;
      } else if (top.state == 2) {
        idx++;
        if (arr[idx] != null) {
          top.node.right = new Node(arr[idx], null, null);
          Pair rp = new Pair(top.node.right, 1);
          st.push(rp);
        } else {
          top.node.right = null;
        }

        top.state++;
      } else {
        st.pop();
      }
    }

    return root;
  }

  public static void display(Node node) {
    if (node == null) {
      return;
    }

    String str = "";
    str += node.left == null ? "." : node.left.data + "";
    str += " <- " + node.data + " -> ";
    str += node.right == null ? "." : node.right.data + "";
    System.out.println(str);

    display(node.left);
    display(node.right);
  }

//   public static void inorder(Node root, ArrayList<Integer>arr)
//   {
//       if(root == null)
//       {
//           return;
//       }
//       inorder(root.left,arr);
//       arr.add(root.data);
//       inorder(root.right,arr);
//   }
//   public static void appraoch2(Node root, int target)
//   {
//       ArrayList<Integer> arr = new ArrayList<>();
//       inorder(root,arr);
//       //insert sorted elements in arraylist
//       int left=0,right=arr.size()-1;
      
//       while(left<right)
//       {
//           int sum=arr.get(left)+arr.get(right);
//           if(sum == target)
//           {
//               System.out.println(arr.get(left)+" "+arr.get(right));
//               left++;
//               right--;
//           }
//           else if(sum<target)
//           {
//               left++;
//           }
//           else
//           {
//               right--;
//           }
//       }
//   }

  public static boolean find(Node node , int data)
  {
      if(node==null)
      {
          return false;
      }
      if(data>node.data)
      {
          return find(node.right,data);
      }
      else if(data<node.data)
      {
          return find(node.left,data);
      }
      else
      {
          return true;
      }
  }
  public static void travelAndPrint(Node root,Node node,int tar)
  {
      if(node == null)
      {
          return ;
      }
      
      travelAndPrint(root,node.left,tar);
      
      int comp = tar-node.data;
      if(node.data < comp)
      {
          if(find(root,comp)==true)
          {
              System.out.println(node.data+" "+comp);
          }
      }
      travelAndPrint(root,node.right,tar);
      
  }
  public static void main(String[] args) throws Exception {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    int n = Integer.parseInt(br.readLine());
    Integer[] arr = new Integer[n];
    String[] values = br.readLine().split(" ");
    for (int i = 0; i < n; i++) {
      if (values[i].equals("n") == false) {
        arr[i] = Integer.parseInt(values[i]);
      } else {
        arr[i] = null;
      }
    }

    int data = Integer.parseInt(br.readLine());

    Node root = construct(arr);
    travelAndPrint(root,root,data);
  }

}
