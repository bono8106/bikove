package stuff

import java.util.Date
import java.util.Calendar
import java.util.GregorianCalendar

object Shipment extends App {
  case class Window(start: Date, end: Date) {}

  case class Shipment(pickup: Window, delivery: Window) {}

  def getTimeWithHour(time: Date, hour: Int): Date = {
    val timeCal = Calendar.getInstance()
    timeCal.setTime(time)
    timeCal.set(Calendar.HOUR_OF_DAY, hour)
    timeCal.set(Calendar.MINUTE, 0)
    timeCal.set(Calendar.SECOND, 0)
    timeCal.set(Calendar.MILLISECOND, 0)
    timeCal.getTime
  }

  def getHourOfDay(time: Date): Int = {
    val timeCal = Calendar.getInstance()
    timeCal.setTime(time)
    timeCal.get(Calendar.HOUR_OF_DAY)
  }

  def getHourCeiling(time: Date): Date = {
    val HOUR_IN_MILLIS = 60 * 60 * 1000
    val hourOffsetMillis: Long = time.getTime % HOUR_IN_MILLIS
    val timeFloorHours: Long = time.getTime / HOUR_IN_MILLIS

    val timeCeilHours: Long = if (hourOffsetMillis > 0) timeFloorHours + 1 else timeFloorHours
    new Date(timeCeilHours * HOUR_IN_MILLIS)
  }

  def nextDay(time: Date): Date = {
    val timeCal = Calendar.getInstance()
    timeCal.setTime(time)
    timeCal.add(Calendar.DATE, 1)
    timeCal.getTime
  }

  val PICKUP_OPEN_HOUR = 9
  val PICKUP_CLOSE_HOUR = 17
  val MIN_WINDOW_HOURS = 2
  val DELIVERY_CLOSE_HOUR = 18
  val MIN_PICKUP_DELIVERY_GAP_HOURS = 1

  def widestPickup(currentTime: Date): Shipment = {
    val pickupOpen = getTimeWithHour(currentTime, PICKUP_OPEN_HOUR)
    val pickupCutoff = getTimeWithHour(currentTime, PICKUP_CLOSE_HOUR - MIN_WINDOW_HOURS)

    val minPickupStart = getHourCeiling(currentTime)

    val pickupStart: Date =
      if (minPickupStart.before(pickupOpen)) {
        pickupOpen
      } else if (minPickupStart.after(pickupCutoff)) {
        nextDay(pickupOpen)
      } else {
        minPickupStart
      }

    val deliveryStart = getTimeWithHour(pickupStart,
        getHourOfDay(pickupStart) + MIN_PICKUP_DELIVERY_GAP_HOURS)

    Shipment(
        Window(pickupStart, getTimeWithHour(pickupStart, PICKUP_CLOSE_HOUR)),
        Window(deliveryStart, getTimeWithHour(deliveryStart, DELIVERY_CLOSE_HOUR)))
  }

  val currentTime = new GregorianCalendar(2015, 12, 30, 3, 1)

  println(widestPickup(currentTime.getTime))
}