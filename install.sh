#! /bin/bash
javac Main.java
echo "Compiling..."
if [ -z "Main.class" ];then
    echo "Compiled"
    if [ $(uname) == "Linux" ];then
        if [ grep -q "sudokuGame" ~/.bashrc ];then

            echo "alias sudokugame=$(java $(pwd)/Main)" >> ~/.bashrc


        fi
    fi




fi