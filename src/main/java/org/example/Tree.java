package org.example;

import java.sql.SQLOutput;
import java.util.Stack;

public class Tree {
    private Node rootNode; // корневой узел
    private int size = 1;

    public Tree() { // Пустое дерево
        rootNode = null;
    }
    public boolean contains(int value) { // поиск узла по значению
        Node currentNode = rootNode; // начинаем поиск с корневого узла
            while (currentNode.getValue() != value) { // поиск покуда не будет найден элемент или не будут перебраны все
                if (value < currentNode.getValue()) { // движение влево
                    currentNode = currentNode.getLeftChild();
                }
                else { //движение вправо
                    currentNode = currentNode.getRightChild();
                }
                if (currentNode == null) { // если потомка нет, то
                    return false;
                }
            }
        if (currentNode.getValue() == value) { //если такой элемент есть
            return true;//подтверди
        }
        return false; // иначе элемента нет
    }
    public void insertNode(int value) { // метод вставки нового элемента
        Node newNode = new Node(); // создание нового узла
        newNode.setValue(value); // вставка данных
        if (rootNode == null) { // если корневой узел не существует
            rootNode = newNode;// то новый элемент и есть корневой узел
        }
        else { // корневой узел занят
            Node currentNode = rootNode; // начинаем с корневого узла
            Node parentNode;
            while (true) // мы имеем внутренний выход из цикла
            {
                parentNode = currentNode;
                if(value == currentNode.getValue()) {   // если такой элемент в дереве уже есть, не сохраняем его
                    return;    // просто выходим из метода
                }
                else  if (value < currentNode.getValue()) {   // движение влево?
                    currentNode = currentNode.getLeftChild();
                    if (currentNode == null){ // если был достигнут конец цепочки,
                        parentNode.setLeftChild(newNode); //  то вставить слева и выйти из методы
                        return;
                    }
                }
                else { // Или направо?
                    currentNode = currentNode.getRightChild();
                    if (currentNode == null) { // если был достигнут конец цепочки,
                        parentNode.setRightChild(newNode);  //то вставить справа
                        return; // и выйти
                    }
                }
            }
        }
    }
    public boolean deleteNode(int value) // Удаление узла с заданным значением
    {
        Node currentNode = rootNode; //текущая-корень
        Node parentNode = rootNode;//родительская-корень
        boolean isLeftChild = true;
        while (currentNode.getValue() != value) { //поиск удаляемого узла
            parentNode = currentNode;
            if (value < currentNode.getValue()) { // Определяем, нужно ли движение влево?
                isLeftChild = true;
                currentNode = currentNode.getLeftChild();
            }
            else { // или движение вправо?
                isLeftChild = false;
                currentNode = currentNode.getRightChild();
            }
            if (currentNode == null)
                return false; // yзел не найден
        }
        if (currentNode.getLeftChild() == null && currentNode.getRightChild() == null) { // узел просто удаляется, если не имеет потомков
            if (currentNode == rootNode) // если узел - корень, то дерево очищается
                rootNode = null;
            else if (isLeftChild)
                parentNode.setLeftChild(null); // если нет - узел отсоединяется, от родителя
            else
                parentNode.setRightChild(null);
        }
        else if (currentNode.getRightChild() == null) { // узел заменяется левым поддеревом, если правого потомка нет
            if (currentNode == rootNode) // если узел - корень, то замена на лев потомок
                rootNode = currentNode.getLeftChild();
            else if (isLeftChild)
                parentNode.setLeftChild(currentNode.getLeftChild());
            else
                parentNode.setRightChild(currentNode.getLeftChild());
        }
        else if (currentNode.getLeftChild() == null) { // узел заменяется правым поддеревом, если левого потомка нет
            if (currentNode == rootNode) // если узел - корень, то замена на прав потомок
                rootNode = currentNode.getRightChild();
            else if (isLeftChild)
                parentNode.setLeftChild(currentNode.getRightChild());
            else
                parentNode.setRightChild(currentNode.getRightChild());
        }
        else { // если есть два потомка, узел заменяется преемником
            Node heir = receiveHeir(currentNode);// поиск преемника для удаляемого узла (путем вызова метода поиска наследника)
            if (currentNode == rootNode) // если узел - корень, то замена на наследника
                rootNode = heir;
            else if (isLeftChild) //если существует наименьший из наибольших
                parentNode.setLeftChild(heir);
            else //если нет - то это будет правый
                parentNode.setRightChild(heir);
        }
        return true; // элемент успешно удалён
    }

    // метод возвращает узел со следующим значением после передаваемого аргументом.
    // для этого он сначала переходим к правому потомку, а затем
    // отслеживаем цепочку левых потомков этого узла.
    private Node receiveHeir(Node node) { //получение наследника
        Node parentNode = node; //родитель преемника
        Node heirNode = node;//потомок
        Node currentNode = node.getRightChild(); // Переход к правому потомку
        while (currentNode != null) // Пока остаются левые потомки
        {
            parentNode = heirNode;// потомка задаём как текущий узел
            heirNode = currentNode;
            currentNode = currentNode.getLeftChild(); // переход к левому потомку
        }
        if (heirNode != node.getRightChild()) // Если преемник не является, правым потомком удаляемого узла,
        { // создать связи между узлами
            parentNode.setLeftChild(heirNode.getRightChild()); //родитель забирает себе потомка преемника, чтобы не потерять его
            heirNode.setRightChild(node.getRightChild()); //связываем преемника с правым потомком удаляемого узла
        }
        return heirNode;// возвращаем приемника
    }

    public void inOrder(Node current) { //цетрированный обход дерева - рекурсивный метод
        if (current != null) { //если узел существует
            inOrder(current.getLeftChild()); //сделать дейтсвие с левым потомком
            System.out.println(current.getValue() + " "); //вывести в консоль текущее значение
            ++size;//счетчик кол-ва узлов
            inOrder(current.getRightChild()); //действие с правым потомком
        }
    }
    public void size(Node current) {
        Tree tree = new Tree();
        System.out.println("size of tree: " + size);
    }
    public Node findNodeByValue(int value) { // поиск узла по значению
        Node currentNode = rootNode; // начинаем поиск с корневого узла
        while (currentNode.getValue() != value) { // поиск покуда не будет найден элемент или не будут перебраны все
            if (value < currentNode.getValue()) { // движение влево?
                currentNode = currentNode.getLeftChild();
            } else { //движение вправо
                currentNode = currentNode.getRightChild();
            }
            if (currentNode == null) { // если потомка нет,
                return null; // возвращаем null
            }
        }
        return currentNode; // возвращаем найденный элемент
    }
    public void printTree() { // метод для вывода дерева в консоль
        Stack globalStack = new Stack(); // общий стек для значений дерева
        globalStack.push(rootNode);
        int gaps = 32; // начальное значение расстояния между элементами
        boolean isRowEmpty = false;
        String separator = "-----------------------------------------------------------------";
        System.out.println(separator);// черта для указания начала нового дерева
        while (isRowEmpty == false) {
            Stack localStack = new Stack(); // локальный стек для задания потомков элемента
            isRowEmpty = true;

            for (int j = 0; j < gaps; j++)
                System.out.print(' ');
            while (globalStack.isEmpty() == false) { // покуда в общем стеке есть элементы
                Node temp = (Node) globalStack.pop(); // берем следующий, при этом удаляя его из стека
                if (temp != null) {
                    System.out.print(temp.getValue()); // выводим его значение в консоли
                    localStack.push(temp.getLeftChild()); // соохраняем в локальный стек, наследники текущего элемента
                    localStack.push(temp.getRightChild());
                    if (temp.getLeftChild() != null ||
                            temp.getRightChild() != null)
                        isRowEmpty = false;
                }
                else {
                    System.out.print("__");// - если элемент пустой
                    localStack.push(null);
                    localStack.push(null);
                }
                for (int j = 0; j < gaps * 2 - 2; j++)
                    System.out.print(' ');
            }
            System.out.println();
            gaps /= 2;// при переходе на следующий уровень расстояние между элементами каждый раз уменьшается
            while (localStack.isEmpty() == false)
                globalStack.push(localStack.pop()); // перемещаем все элементы из локального стека в глобальный
        }
        System.out.println(separator);// подводим черту
    }
}