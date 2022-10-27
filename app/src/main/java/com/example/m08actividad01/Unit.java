package com.example.m08actividad01;

public class Unit {
    String name;
    double squaredMeterEquivalence;

    public Unit(String name, double squaredMeterEquivalence) {
        this.name = name;
        this.squaredMeterEquivalence = squaredMeterEquivalence;
    }

    @Override
    public String toString(){
        return name;
    }
}

