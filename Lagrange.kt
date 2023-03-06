fun main() {
    val Points = listOf(1.0, 1.1, 1.2, 1.3, 1.4, 1.5, 1.6, 1.7, 1.8, 1.9, 2.0)
    val Values = listOf(2.7182, 3.0041, 3.3201, 3.6692, 4.0552, 4.4816, 4.9530, 5.4739, 6.0496, 6.6858, 7.3890)

    val X = 1.43
    //val X = readLine()
    var MultRes = 1.0
    var AdditRes = 0.0

    for (i in 0 until Points.size){
        for (j in 0 until Points.size){
            if(i != j){
                MultRes *= (X - Points[j])/(Points[i]-Points[j])
            }
        }
        AdditRes += MultRes * Values[i]
        MultRes = 1.0
    }
    println("The result of the program execution is $AdditRes")

}