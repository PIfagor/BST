package implemets;
public class BST<Key extends Comparable<Key>, Value>{

private Node root;
private class Node{};
public void put(Key key, Value val){};
public Value get(Key key){
	return null;};
public void delete(Key key){};
public Iterable<Key> iterator(){
	return null;};
public Key min(){
	return null;}; //мінімальний ключ в таблиці.
public Key max(){}; //максимальний ключ в таблиці.
public Key floor(Key key){}; //максимальний ключ в таблиці, що менше або дорівнює заданому.
public Key ceiling(){}; //найменший ключ в таблиці що менше або дорівнює заданому.
public int rank(Key key) {};
public int size(){};
}