fun main(){
    println("Polynom :")
    println("f(x) = 3*(x^4) - 3*(x^3) - 2*(x^2) + 3*x + 1")
    val Points = arrayOf(0,5,10,15,20)
    val Values = arrayOf(1,1466,26831,141346,455261)

    val table = table(Values)

    printer(table)

}
fun printer(mass:Array<Array<Int>>){
    println("Table of Finite Differences")
    for(i in 0 until mass.size){
        for(j in 0 until mass.size){
            print("${mass[j][i]}" + " ")
        }
        println()
    }
}