package DataStructures.Heap;

/**
 * @Auther gongfukang
 * @Date 2017/12/24 10:07
 * 大顶堆
 */
public class Heap {
    private Node[] heapArray;
    private int maxSize;
    private int currentSize;

    public Heap(int mx){
        maxSize=mx;
        currentSize=0;
        heapArray=new Node[maxSize];
    }

    public boolean isEmpty(){
        return currentSize==0;
    }

    public boolean insert(int key){
        if(currentSize==maxSize)
            return false;
        Node newNode=new Node(key);
        heapArray[currentSize]=newNode;
        trickleUp(currentSize++);
        return true;
    }

    public void trickleUp(int index){
        int parent=(index-1)/2;
        Node bottom=heapArray[index];

        while (index>0&&heapArray[parent].getKey()<bottom.getKey()){
            heapArray[index]=heapArray[parent];
            index=parent;
            parent=(parent-1)/2;
        }
        heapArray[index]=bottom;
    }

    public Node remove(){
        Node root=heapArray[0];
        heapArray[0]=heapArray[--currentSize];
        trickleDown(0);
        return root;
    }

    public void trickleDown(int index){
        int largeChild;
        Node top=heapArray[index];

        while (index<currentSize/2){
            int leftChild=2*index+1;
            int rightChild=leftChild+1;

            if(rightChild<currentSize&&heapArray[leftChild].getKey()<heapArray[rightChild].getKey())
                largeChild=rightChild;
            else
                largeChild=leftChild;

            if(top.getKey()>=heapArray[largeChild].getKey())
                break;

            heapArray[index]=heapArray[largeChild];
            index=largeChild;
        }
        heapArray[index]=top;
    }

    public boolean change(int index,int newValue){
        if(index<0||index>=currentSize)
            return false;
        int oldValue=heapArray[index].getKey();
        heapArray[index].setKey(newValue);

        if(oldValue<newValue)
            trickleUp(index);
        else
            trickleDown(index);
        return true;
    }

    public void displayHeap(){
        System.out.print("heapArrary: ");
        for(int m=0;m<currentSize;m++){
            if(heapArray[m]!=null)
                System.out.print(heapArray[m].getKey()+" ");
            else
                System.out.print("-- ");
        }
        System.out.println();

        int nBlacks=32;
        int itemsPerRow=1;
        int column=0;
        int j=0;
        String dots="***********************************";

        while (currentSize>0){
            if(column==0)
                for(int k=0;k<nBlacks;k++)
                    System.out.print(' ');
            System.out.print(heapArray[j].getKey());
            if(++j==currentSize)
                break;

            if(++column==itemsPerRow){
                nBlacks/=2;
                itemsPerRow*=2;
                column=0;
                System.out.println();
            }else
                for(int k=0;k<nBlacks*2-2;k++)
                    System.out.print(' ');
        }
        System.out.println("\n"+dots+dots);
    }
}
