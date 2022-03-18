package com.example.processor

import com.google.auto.service.AutoService
import com.squareup.kotlinpoet.*
import java.io.File
import javax.annotation.processing.AbstractProcessor
import javax.annotation.processing.Processor
import javax.annotation.processing.RoundEnvironment
import javax.lang.model.SourceVersion
import javax.lang.model.element.TypeElement

@AutoService(Processor::class)
class ConstantProcessor : AbstractProcessor() {

    companion object {
        const val KAPT_KOTLIN_GENERATED = "kapt.kotlin.generated"
    }

    override fun getSupportedSourceVersion(): SourceVersion = SourceVersion.latestSupported()

    override fun getSupportedAnnotationTypes() = mutableSetOf(AppConstant::class.java.name)

    override fun process(annotations: MutableSet<out TypeElement>?, roundEnv: RoundEnvironment?): Boolean {
        val elements = roundEnv?.getElementsAnnotatedWith(AppConstant::class.java) ?: run { return true }
        val generatedSource = processingEnv.options[KAPT_KOTLIN_GENERATED] ?: run { return true }
        var objectBuilder: TypeSpec.Builder? = null

        if (annotations == null || annotations.isEmpty()) return true
        if (elements.isEmpty()) return true

        val packageName = "com.example"
        var fileName = ""

        for (element in elements) {
            val annotated = element.getAnnotation(AppConstant::class.java)
            val propName = annotated.propName
            val propValue = annotated.propValue
            fileName = "${element.simpleName}".capitalize()

            val propBuilder = PropertySpec.builder(
                name = propName,
                type = ClassName("kotlin", "String"),
                modifiers = arrayOf(KModifier.CONST, KModifier.FINAL)
            ).mutable(false).initializer("\"$propValue\"")

            objectBuilder = TypeSpec.objectBuilder(fileName).apply {
                addProperty(propBuilder.build())
            }
        }

        val file = FileSpec.builder(packageName, fileName)
            .addType(objectBuilder!!.build())
            .build()
        file.writeTo(File(generatedSource))

        return true
    }

}