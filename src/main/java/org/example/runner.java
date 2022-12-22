package org.example;


public class runner {
    public static void main(String[] args) {
        Tree tree = new Tree();
        // вставляю узлы в дерево:
        tree.insertNode(6);
        tree.insertNode(8);
        tree.insertNode(5);
        tree.insertNode(8);
        tree.insertNode(2);
        tree.insertNode(9);
        tree.insertNode(7);
        tree.insertNode(4);
        tree.insertNode(10);
        tree.insertNode(3);
        tree.insertNode(1);

        tree.printTree();// отображение дерева после вставвки:

        tree.deleteNode(5);// удаляю один узел и вывожу оставшееся дерево в консоли
        tree.printTree();//отображение дерева после удаления:

        System.out.println("is requested element exists?: " + tree.contains(7)); //есть ли узел с таким значением - да/нет
        Node foundNode = tree.findNodeByValue(6); //ищу узел по значению - корневой узел для обхода и отображения размера
        tree.inOrder(foundNode); //обход всех узлов с отображением в консоли
        tree.size(foundNode); //размер дерева
    }
}