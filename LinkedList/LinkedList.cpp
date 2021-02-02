#include <cstddef>
#include <iostream>

using namespace std;

struct Node {
    int val;
    Node* next;
    Node* previous;
};

class LinkedList {
    private:

        Node* head;
        int size;

        void addAfter(Node* prev, int val) {
            Node* after;
            after = prev->next;
            prev->next = new Node;
            prev->next->val = val;
            prev->next->next = after;
            prev->next->previous = prev;
            after->previous = prev->next;
            size++;
        }

        void removeAfter(Node* prev) {
            Node* after = prev->next->next;
            prev->next = after;
            after->previous = prev;
            size--;
        }

        void addFirst(int n) {
            if (n!= NULL) {
                addAfter(head,n);
            }
        }

        void addLast(int n) {
            if (n!= NULL) {
                addAfter(head->previous,n);
            }
        }

    public:
        void add(int n, int position) {
            if (position >=0 || position < size) {
                Node* before = head;
                for(int i=0; i< position; i++) {
                    before = before->next;
                }
                addAfter(before,n);
            }
        }

        void removeFirst() {
            if (size >0 ) {
                removeAfter(head);
            }
        }

        void removeLast() {
            if (size > 0) {
                removeAfter(head->previous->previous);
            }
        }

        int get(int position) {
            if (position >=0 || position < size) {
                Node* current = head->next;
                for(int i=0; i< position; i++) {
                    current = current->next;
                }
                return current->val;
            }
            return -1;
        }
        void printContents() {
            Node* temp = head;
            while (temp!=NULL && temp->next!=NULL) {
                cout << temp->val << "\n";
                temp = temp->next;
            }
        }
        int getSize() {
            return size;
        }
        LinkedList() {
            head = new Node;
            head->previous = head->next = head;
        }
};


int main() {
    LinkedList test;
    test.add(1,0);
    for (int i = 0; i< 10; i++) {
        test.add(i,i);
    }
    test.printContents();

}