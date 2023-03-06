import kotlin.math.exp
import kotlin.math.pow

fun main(args: Array<String>) {
    val h1 = 0.1
    val h2 = 0.01
    val h3 = 0.001
    val adamsEx = AdamsExact(h2)
    val adamsExpSol = explicitAdams(1.0,h2)
    val adamsImpSol = implicitAdams(1.0,h2)
    for(i in 0 until adamsEx.size){
        println("${adamsEx[i]} == ${adamsExpSol[i]} == ${adamsImpSol[i]}")
    }
    //
}
fun explicitAdams(y0:Double,h:Double):List<Double>{
    val y = mutableListOf<Double>()
    var q0:Double
    var q1:Double
    var q2:Double
    var xNext = 0.0
    y.add(y0)
    for (i in 0 until 2){
        xNext += h
        q0 = current(xNext, y[i])
        q1 = current(xNext + h/3,y[i]+h*q0*2/3)
        q2 = current(xNext + h*2/3,y[i]+h*q1*2/3)
        y.add(y[i]+h/4*(q0+3*q2))
    }
    for(i in 2 until (1/h).toInt()){
        xNext += h
        y.add(y[i]+(h/12)*(23*(current(xNext,y[i]))-16*(current(xNext-h,y[i-1]))+5*(current(xNext-2*h,y[i-2]))))
    }
    return y
}
fun implicitAdams(y0:Double,h:Double):List<Double>{
    val y = mutableListOf<Double>()
    var k0:Double
    var k1:Double
    var k2:Double
    var k3:Double
    var xNext = 0.0
    y.add(y0)
    for (i in 1 until 3){
        xNext += h
        k0 = current(xNext, y[i-1])
        k1 = current(xNext + h/3,y[i-1]+h*k0/3)
        k2 = current(xNext + 2*h/3,y[i-1]-h*k0/3+h*k1)
        k3 = current(xNext + h,y[i-1]+h*k0-h*k1+h*k2)
        y.add(y[i-1]+h/8*(k0+3*k1+3*k2+k3))
    }
    for(i in 3 .. (1/h).toInt()){
        xNext += h
//        y.add((24*h*(19*(current(xNext-h,y[i]))-5*(current(xNext-2*h,y[i-1]))+(current(xNext-3*h,y[i-2])))+h*9*xNext.pow(2))/(24-9*h))
//        y.add((-19*(current(xNext-h,y[i-1]))*h+5*(current(xNext-2*h,y[i-2]))*h-(current(xNext-3*h,y[i-3]))*h-9*h*xNext.pow(2)-24*y[i-1])/3*(-8+3*h))
        y.add( (h*(19*current(xNext-h,y[i-1]) - 5*current(xNext-2*h,y[i-2]) + current(xNext-3*h,y[i-3]))+(24*y[i-1]) + h*9*xNext.pow(2))/(24-9*h) )
    }
    return y
}
fun AdamsExact(h:Double):List<Double>{
    val y = mutableListOf<Double>()
    var xNext = 0.0
    for(i in 0 .. (1/h).toInt()){
        y.add(solution(xNext))
        xNext+=h
    }
    return y
}
fun solution(x:Double):Double{
    return -x.pow(2)-2*x+3*exp(x)-2
}
fun current(x:Double,y:Double):Double {
    return y + x.pow(2)
}