package com.example.processor

import com.google.auto.service.AutoService
import com.squareup.kotlinpoet.*
import java.io.File
import javax.annotation.processing.AbstractProcessor
import javax.annotation.processing.ProcessingEnvironment
import javax.annotation.processing.Processor
import javax.annotation.processing.RoundEnvironment
import javax.lang.model.SourceVersion
import javax.lang.model.element.TypeElement
import javax.tools.Diagnostic

@AutoService(Processor::class)
class ConstantProcessor : AbstractProcessor() {

    override fun getSupportedSourceVersion(): SourceVersion {
        return SourceVersion.latestSupported()
    }

    override fun getSupportedAnnotationTypes(): MutableSet<String> {
        return mutableSetOf(MyConstant::class.java.name)
    }

    override fun process(annotations: MutableSet<out TypeElement>?, roundEnv: RoundEnvironment?): Boolean {
        if (roundEnv == null)  return true

        if (annotations == null || annotations.isEmpty()) return true

        val elements = roundEnv.getElementsAnnotatedWith(MyConstant::class.java)
        if (elements.isEmpty()) return true

        val generatedSource = processingEnv.options[KAPT_KOTLIN_GENERATED] ?: run {
            return true
        }

        val packageName = "com.example"
        var fileName = ""

        // create object builder
        var objectBuilder: TypeSpec.Builder? = null

        for (element in elements) {
            val annotated = element.getAnnotation(MyConstant::class.java)
            val propName = annotated.propName
            val propValue = annotated.propValue
            fileName = "${element.simpleName}".capitalize()

            // crate property
            val propBuilder = PropertySpec.builder(
                name = propName,
                type = ClassName("kotlin", "String"),
                modifiers = arrayOf(KModifier.CONST, KModifier.FINAL)
            ).mutable(false).initializer("\"$propValue\"")

            objectBuilder = TypeSpec.objectBuilder(fileName).apply {
                addProperty(propBuilder.build())
            }
        }

        // create a file
        val file = FileSpec.builder(packageName, fileName)
            .addType(objectBuilder!!.build())
            .build()
        file.writeTo(File(generatedSource))

        return true
    }

    companion object {
        const val KAPT_KOTLIN_GENERATED = "kapt.kotlin.generated"
    }
}