# DFA-minimization

## Vistazo general de la aplicación

<img src="imgs/global-view.jpg" alt="global ui image" width = "650%" height="65%">

## Minimizar una máquina

### Máquina de Moore

Partiendo de una máquina con los siguientes estados:

<img src="imgs/moore-machine.png" alt="global ui image" width = "20%" height="20%">

En el programa se introduce la información general de la máquina y se selecciona el tipo de máquina (Moore) en este ejemplo:
- Alfabeto de entrada {0,1} 
- Alfabeto de salida {0,1}
- Número de estados: 11

<img src="imgs/moore-1.png" alt="global ui image" width = "50%" height="50%">

Tras dar click al botón "Save" de color verde, en la primera tabla "State Table" se crean 11 estados identificados con una letra mayuscula. El estado inicial corresponde al estado A. Así mismo se muestran las casillas que permiten ingresar las transiciones de acuerdo las valores de entrada, y las salidas correspondientes a cada estado. Para cada caso hay disponible un menú desplegable que permite seleccionar el estado de la transición y el valor de la salida.


<img src="imgs/moore-2.png" alt="global ui image" width = "50%" height="50%">

Agregando la información de la máquina se llena la tabla. Una vez llena se da click al botón "Crear Máquina", así se habilita el botón minimizar.

<img src="imgs/moore-3.png" alt="global ui image" width = "50%" height="50%">

Tras dar click en minimizar se realiza el algoritmo de minimización y se muestra su resultado en la tabla "State Table (Minimized)". El nombre de cada estado corresponde a las agrupaciones realizadas en el algoritmo de minimización, y así mismo se ajustan las transiciones y la salida. El estado inicial será aquel que tenga el estado A.

<img src="imgs/moore-4.png" alt="global ui image" width = "50%" height="50%">

### Máquina de Mealy

Máquina de ejemplo:

<img src="imgs/mealy-machine.png" alt="global ui image" width = "20%" height="20%">

Siguiendo los pasos para la máquina de Moore tambien se llega a la máquina de Mealy con la diferencia de que cada transición tiene asociado un valor de salida, y los estados carecen de una salida en si mismos.

<img src="imgs/mealy-1.png" alt="global ui image" width = "50%" height="50%">


