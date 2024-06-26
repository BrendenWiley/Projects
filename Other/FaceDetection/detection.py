import cv2
import sys

cap = cv2.VideoCapture(0)
cascPath = "haarcascade_frontalface_default.xml"
# Create the cascade
faceCascade = cv2.CascadeClassifier(cv2.data.haarcascades + 'haarcascade_frontalface_default.xml')
eye_cascade = cv2.CascadeClassifier(cv2.data.haarcascades + 'haarcascade_eye.xml')
# Read the image

while True:
    ret, image = cap.read()
    gray = cv2.cvtColor(image, cv2.COLOR_BGR2GRAY)
    # detect faces in the image
    faces = faceCascade.detectMultiScale(
    gray,
    scaleFactor=1.3,
    minNeighbors=5,
 
)
# draw a rectangle around the faces
    for (x, y, w, h) in faces:
        cv2.rectangle(image, (x, y), (x+w, y+h), (0, 255, 0), 2)

        roi_gray = gray[y:y+w, x:x+w, ]
        roi_color = image[y:y+h, x:x+w,]
        eyes = eye_cascade.detectMultiScale(
        roi_gray,
        scaleFactor=1.3,
        minNeighbors=7,
        )
        for (ex, ey, ew, eh) in eyes:
            cv2.rectangle(roi_color, (ex, ey), (ex + ew, ey + eh), (0, 255, 0), 3)
            
    cv2.imshow('image', image)
    
    if cv2.waitKey(1) == ord('q'):
        break


print("Found {0} faces!".format(len(faces)))
print("Found {0} eyes!".format(len(eyes)))
cap.release()
cv2.destroyAllWindows()
