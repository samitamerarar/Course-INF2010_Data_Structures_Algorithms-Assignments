/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author maitr
 */
public class DifferentOrderTrees extends Exception {

    public DifferentOrderTrees() {
        super("Erreur : on ne peut pas fusionner deux arbres d'ordres différents");
    }

    public DifferentOrderTrees(String s) {
        super(s);
    }
}
