# Wanna-Form
โปรเจคปี 2 เทอม 1 วิชา Fundamental programming 2

จุดประสงค์ : เพื่อสร้างการโปรแกรมการสอบ online โดยตรวจสอบได้ว่าผู้สอบทำข้อสอบอยู่ตลอด
โปรเจคนี้ใช้ IntelliJ IDEA ในการเขียน

requirement : ติดตั้งฟอนต์ Freehand521 ในเครื่องก่อนรัน

![image main](https://user-images.githubusercontent.com/59200533/244873113-354dde01-33e8-4cec-9a4c-d5fb0484b16a.png)

รายละเอียด : ใช้ภาษา java ในการเขียนโปรแกรมเพื่อการกรอกแบบฟอร์ม โดยมี 2 โหมด คือ ทำแบบฟอร์ม และ สร้างแบบฟอร์ม
- ทำแบบฟอร์ม
  ผู้ทำแบบฟอร์ม จะต้องเปิดกล้องและทำแบบฟอร์มตามที่เลือกไว้ โดยรายละเอียดคนทำแบบฟอร์มจะเป็นคนกำหนด 
  หากกล้อง detect ไม่เจอใบหน้าผู้ทำถึงช่วงเวลานึงจะออกจากแบบฟอร์มและไม่บันทึกการทำ 
  
  ![image user1](https://user-images.githubusercontent.com/59200533/244873362-71b7fa83-e0e8-4138-baa2-bb132f818856.png)
  
  ![image user2](https://user-images.githubusercontent.com/59200533/244873443-317fbca8-2f18-4e18-84a7-e02ae44a8837.png)
  
- สร้างแบบฟอร์ม
  ผู้สร้างแบบฟอร์ม สามารถกำหนดจำนวนคำถาม , รูปแบบการตอบ , เวลาการทำ 
  หากมีคนทำแบบฟอร์มนั้นแล้วจะแสดงเปอร์เซ็นต์การยิ้มของผู้ทำทั้งหมดของแบบฟอร์มนั้นได้ด้วย (smile detection)
  
  ![image creater1](https://user-images.githubusercontent.com/59200533/244873146-6bf675e3-58e9-4cd8-8eae-53f50ecb007e.png)
  
  ![image creater2](https://user-images.githubusercontent.com/59200533/244873263-c1842af1-a0fb-44bc-8e5f-92f1065a666c.png)
  
  ![image creater3](https://user-images.githubusercontent.com/59200533/244873333-4c62c469-9e6c-46ee-9224-e41a808e78d6.png)
 
 ไฟล์รันเพื่อเปิดโปรแกรม : src/WindowsMain.java
